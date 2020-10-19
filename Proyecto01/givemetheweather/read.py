import importlib.resources as pkg_resources
import json
from givemetheweather.place import Place
from . import resources


def load_iata_dic():
    ''' Loads a dictionary with the IATA codes '''
    iatas_json = pkg_resources.read_text(resources, 'iatas.json')
    iatas = json.loads(iatas_json)
    return iatas


def load_cities_dic():
    ''' Loads a dictionary with the name of the cities '''
    cities_json = pkg_resources.read_text(resources, 'cities.json')
    cities = json.loads(cities_json)
    return cities


def load_iata_coordinates():
    ''' Loads a dictionary with the IATA codes and its coordinates '''
    coordinates_json = pkg_resources.read_text(
        resources, 'coordinates_iata.json')
    coordinates = json.loads(coordinates_json)
    return coordinates


iatas = load_iata_dic()
cities = load_cities_dic()
iata_coordinates = load_iata_coordinates()


def prepare_string(string):
    ''' Formate the line for further reading'''
    string = string.replace(" ", "*")
    string = string.replace(",", " ")
    return string


def quita_espacios(string):
    ''' Formate the line for further reading'''
    string = string.replace(" ", "*")
    return string


def pre_split(cadena):
    cadena = cadena.replace(",", " ")
    return cadena


def get_city(iata):
    ''' Returns the city name given the IATA code'''
    try:
        city = iatas[iata]
        return city
    except KeyError:
        return None


def get_iata(city):
    ''' Returns the IATA code given the city'''
    try:
        iata = cities[city]
        return iata
    except KeyError:
        return None


def get_location(iata):
    ''' Returns the coordinates given its IATA code'''
    try:
        coordinates = iata_coordinates[iata]
        return coordinates
    except KeyError:
        return None


def valid_file(dataset):
    try:
        open("resources/"+dataset, "r")
        return "yey"
    except IOError:
        return None
    

def read_which_data(dataset):
    ''' Reads a file and determines whether if it's trying to read the first
    dataset, the second, or an invalid file. 
    
    Calls the appropiate method or prints an error message.
    '''
    one = ["origin", "destination", "origin_latitude", "origin_longitude", "destination_latitude", "destination_longitude"]
    two = ["destino", "salida", "llegada", "fecha*de*salida"]
    which = -1
    i = 0
    j = 0
    for line in dataset:
        if i == 0:
            line = prepare_string(line)
            columns = line.split()
            if columns == one:
                which = 1
            elif columns == two:
                which = 2
        else:
            break
    if which == 1:
        return read_dataset_type1(dataset)
    elif which == 2:
        return read_dataset_type2(dataset)
    else:
        raise Exception('Wrong files')


def read_dataset_type1(file):
    ''' Read a dataset with the following format:
    "origin	destination	origin_latitude	origin_longitude	destination_latitude
    destination_longitude"

    Returns
    -------
    tuple
        with the flights and places
    '''
    cont = 0
    places = {}
    flights = {}
    for line in file:
        if cont == 0:
            cont += 1
            continue
        line = prepare_string(line)
        aux = line.split()
        iata = aux[0]
        city = get_city(iata)
        origin = Place(city, aux[2], aux[3])

        # If the place is not found in the dic
        if city in places:
            origin = places[city]
        else:
            places[city] = origin

        iata = aux[1]
        city = get_city(iata)
        destination = Place(city, aux[4], aux[5])
        
        # If the place is not found in the dic
        if city in places:
            destination = places[city]
        else:
            places[city] = destination

        flights[cont] = (origin, destination)
        cont += 1
    return (flights, places)


def read_dataset_type2(file):
    '''
    Read a dataset with the following format:
    "destination	    origin  	arrive_time 	date"

    Returns
    -------
    tuple
        with the flights and places
    '''
    cont = 0
    places = {}
    flights = {}
    default_origin = Place('Mexico City', 19.4363, -99.072098)
    places['Mexico City'] = default_origin
    for line in file:
        if cont == 0:
            cont += 1
            continue
        line = prepare_string(line)
        aux = line.split()
        aux[0] = aux[0].replace("*", " ")

        # The first element is the name of the city
        if get_iata(aux[0]) is not None:
            city = aux[0]
        # The first element is a IATA Code
        elif get_city(aux[0]) is not None:
            city = get_city(aux[0])
        else:
            continue
        coords = get_location(get_iata(city))
        destination = Place(city, coords['lat'], coords['lng'])
        places[city] = destination
        flights[cont] = (default_origin, destination)

        cont += 1
    return (flights, places)
