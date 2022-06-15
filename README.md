Сервис, который обращается к сервису курсов валют, и отдает gif в ответ:
если курс по отношению к рублю за сегодня стал выше вчерашнего, то приложение отдает рандомную gif отсюда https://giphy.com/search/rich 
если ниже - отсюда https://giphy.com/search/broke

С Docker:
docker build . -t RateOfExchange
docker run -p 8080:8080 -t RateOfExchange

С Gradle:
Запускаем gradlew bootRun.
После старта приложения открываем в браузере страницу по адресу http://localhost:8080/currencies/course?symbols=[EUR], 
вместо [EUR] необходимо указать код валюты, например USD. В качестве базовой валюты выступает USD. 
Настройки базовой валюты и другие параметры можно поменять в application.properties файле. 
Приложение возвращает клиенту JSON объект c адресом GIF картинки.
