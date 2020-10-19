import os
from givemetheweather.place import Place
from givemetheweather.weather_requests import WeatherCity
from givemetheweather.weather_api import OpenWeather


api_key = os.environ.get('OPENWEATHER')
api = OpenWeather(api_key)
weather_checker = WeatherCity(api)


def verify_weather(place: Place) -> int:
    ''' Verify the weather of a place and return the total
    num of requests made
    
    Parameters
    ----------
    place: Place
        the place which weather will be verified
    
    Returns
    -------
    int
        the total number of requests made
    '''
    if len(place.name) > 0:
        response = weather_checker.get_by_name(place)
        if response.status_code == 200:
            add_weather(place, response)
            return 1
        elif response.status_code == 401:
            raise ConnectionError('Unauthorized')
        else:
            return 1 + verify_weather_by_coordinates(place)
    else:
        return verify_weather_by_coordinates(place)


def verify_weather_by_coordinates(place: Place) -> int:
    ''' Verify the weather of a place based in its
    coordinates and return the num of requests made 
    
    Parameters
    ----------
    place: Place
        the place which weather will be verified
    
    Returns
    -------
    int
        the total number of requests made
    '''
    
    if not place.valid_coordinates():
        make_not_found(place)
        return 0

    response = weather_checker.get_by_coordinates(place)
    if response.status_code == 200:
        add_weather(place, response)
    elif response.status_code == 401:
        raise ConnectionError('Unauthorized')
    else:
        make_not_found(place)
    return 1


def add_weather(place: Place, response):
    ''' Add the weather info to the place 

    Parameters
    ----------
    place: Place
        the place which weather will be verified
    response
        the response which contains the weather info
    '''
    data = response.json()
    place.weather = data['main']
    place.weather['description'] = data['weather'][0]['description']
    place.status = 200


def make_not_found(place: Place):
    ''' Add the status "not found" to the place  '''
    place.status = 404
