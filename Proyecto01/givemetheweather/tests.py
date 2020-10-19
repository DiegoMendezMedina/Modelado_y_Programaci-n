from givemetheweather.place import Place
from givemetheweather.weather_controller import verify_weather
from givemetheweather.read import read_which_data
import importlib.resources as pkg_resources
from . import resources


def test_api():
    ''' Test the functionality of the API
    The intend is to demostrate that returns the
    correct object with the correct attributes
    '''

    # Real place with accurate coordinates
    london = Place("London", 51.509865, -0.118092)
    verify_weather(london)
    assert london.status == 200

    # Unreal place
    asgard = Place("Asgard", 414, 213)
    verify_weather(asgard)
    assert asgard.status == 404

    # Wrong typed place but accurate coordinates
    guadalajara = Place("Guadlajra", 20.6736, -103.344)
    verify_weather(guadalajara)
    assert guadalajara.status == 200


def test_read():
    ''' Test the method that reads the datasets'''

    dataset1 = pkg_resources.read_text(resources, 'dataset1.csv')
    flights, places = read_which_data(dataset1.splitlines())

    # Check if there are values in the dic
    assert len(places) > 0

    for key in places:
        # Check all the instance are Places
        assert isinstance(places[key], Place)


if __name__ == '__main__':
    test_api()
    test_read()
