class Place:
    '''
    A class used to representate a place

    Attributes
    ----------
    name : str
        the name of the place
    lat : float
        the latitude where the place is located
    lng : float
        the longitude where the place is located
    weather
        the current weather of the place
    '''

    weather = None

    def __init__(self, name: str, lat: float, lng: float):
        self.name = name
        self.lat = lat
        self.lng = lng

    def valid_coordinates(self):
        ''' Check if the current coordinates are valid'''

        lat = self.lat
        lng = self.lng

        if not -90 <= lat <= 90:
            return False

        if not -180 <= lng <= 180:
            return False

        return True
    
    def __str__(self):
        return str(self.name)

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.__dict__ == other.__dict__
        else:
            return False

    def __ne__(self, other):
        return not self.__eq__(other)
