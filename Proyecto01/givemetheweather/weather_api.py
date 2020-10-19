import requests


class WeatherAPI:
    def get_by_coordinates(self, lat: float, lng: float):
        ''' Get the weather of a place given its coordinates 
        
        Parameters
        ----------
        lat : float
            the latitude of the place
        lng : lng
            the longitude of the place

        Returns
        -------
        response
            the response of the API
        '''

    def get_by_city(self, city_name: str, country_code: str = ''):
        ''' Get the weather of place given its name
        

        Parameters
        ----------
        city_name : str
            the name of the city
        contry_code: str, optional
            the country code of the city
        
        Returns
        -------
        response
            the response of the API
        '''


class OpenWeather(WeatherAPI):
    ''' Use the OpenWeatherApi
    More information: https://openweathermap.org/api '''

    base_url = 'https://api.openweathermap.org/data/2.5/weather'

    def __init__(self, api_key: str):
        self.api_key = api_key

    def get_by_coordinates(self, lat: float, lng: float):
        ''' Overrides WeatherAPI.get_by_coordinates()'''
        payload = {'lat': lat, 'lon': lng, 'appid': self.api_key, 'units': 'metric'}
        r = requests.get(self.base_url, params=payload)
        return r

    def get_by_city(self, city_name: str, country_code: str = ''):
        ''' Overrides WeatherAPI.get_by_city() '''
        payload = {'q': city_name, 'appid': self.api_key, 'units': 'metric'}
        r = requests.get(self.base_url, params=payload)
        return r



