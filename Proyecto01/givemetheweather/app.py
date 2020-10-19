import time
import sys
from givemetheweather.weather_controller import verify_weather
from givemetheweather.read import read_which_data


def run(): 
    try:
        with open(sys.argv[1], 'r') as f:
            dataset = f.readlines()
            flights, places = read_which_data(dataset)
            verify_all(places.values())
            for key in flights:
                print_flight(flights[key])
    except Exception:
        print("Nothing to show :(")


def verify_all(places: list):
    ''' Verifies the weather of all the places '''
    requests_made = 0
    limit = 60
    for place in places:
        try:
            requests_made += verify_weather(place)
        # Bad object
        except TypeError:
            continue
        # Unauthorized
        except ConnectionError:
            break
        if requests_made > limit - 2:
            time.sleep(60)
            requests_made = 0


def print_flight(flight: tuple):
    ''' Prints the information of a flight '''

    try: 
        origin, destination = flight
        data = {
            'origin': origin.name,
            'dest': destination.name,
            'origin_description': origin.weather['description'],
            'dest_description': destination.weather['description'],
            'origin_temp': origin.weather['temp'],
            'dest_temp': destination.weather['temp']
        }
        message = ''' Origin: {origin} | Weather: "{origin_description}"  Temperature: {origin_temp}°\n
        Destination: {dest} | Weather: "{dest_description}"  Temperature: {dest_temp}°\n
        '''
        print(message.format(**data))
    except Exception:
        data = {
            'origin': origin.name,
            'dest': destination.name,
        }
        message = 'Origin: {origin} | Destination: {dest}'
        print(message.format(**data))

