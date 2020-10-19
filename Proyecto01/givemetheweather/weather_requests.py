from givemetheweather.weather_api import WeatherAPI
from givemetheweather.place import Place


class WeatherCity:
    ''' A class which control the requests
    
    Attributes
    ----------
    weather_api : WeatherAPI
        the api which is going to be used to get the
        weather
     '''

    def __init__(self, weather_api: WeatherAPI):
        self.weather_api = weather_api

    def get_by_name(self, city: Place):
        ''' Get the weather of a city using its name
        
        Parameters
        ----------
        city: Place
            the city which weather is searched

        Returns
        -------
        response
            the response with the weather info according to
            the API used
         '''
        if city is None:
            raise TypeError
        response = self.weather_api.get_by_city(city.name)
        return response

    def get_by_coordinates(self, city: Place):
        ''' Get the weather of a city using its coordinates
        
        Parameters
        ----------
        city: Place
            the city which weather is searched

        Returns
        -------
        response
            the response with the weather info according to
            the API used
         '''        
        if city is None:
            raise TypeError
        response = self.weather_api.get_by_coordinates(city.lat, city.lng)
        return response
