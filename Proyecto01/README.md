Done by [José Luis Onofre Franco](https://github.com/luis3u) and [Diego Méndez Medina](https://github.com/DiegoMendezMedina)
First, it's needed to add the OpenWeather as an enviroment variable to be able to execute the requests properly.

```shell
export OPENWEATHER='c1505749502e2b1373f897f4aed74c6c'
```

Now, you can execute the program with the following command:

```shell
python3 -m givemetheweather <dataset>
```

It's important to clarify the datasets  the app accepts, which is a _.csv_  file, follows this format:
```
origin,destination,origin_latitude,origin_longitude,destination_latitude,destination_longitude
```

or

```
destino,salida,llegada,fecha de salida
```

<hr>

Run tests (it's necessary to add before the API key):

```shell
python3 -m givemetheweather.tests
```

