
Проект "notes versus shopping list" удобные заметки и списки покупок
-----------------------------------

#### Основной функционал:
* список заметок (одновные поля: дата, название заметка, сама заметка)
* к заметке можно добавить список покупок/список дел (товары можно выбрать из словаря или создать самому, 
можно указывать количество/вес товаров, можно задавать цвет для позиций/строчки, 
вычеркивать что уже куплено/выделять серым, можно включить отображение цены, тогда внизу списка появиться итог/сумма,
включить отображение процента выполнения)
* заметок можно добавить неограниченное количество (ограничено только памятью вашего телефона)

#### Дополнительный функционал:
* заметку/список покупок можно переслать другу/жене в месенджер
* к товарам/покупкам можно добавить фото
* интеграция с гугл календарем (можно создать напоминание, можно перепланировать дела на другую дату)
* заметки можно записывать голосом
* удобный виджет на главный экран телефона
* наше приложение поддерживает Смарт-часов на Android 

#### Этапы разработки:
1. приложение с неограниченным количеством заметок (с названием, датой и со списком дел/покупок)
основные функции - в списке покупок видно что куплено, а что еще нет, виден общий процент выполнения,
к товару можно добавить цену + видно итоговую сумму
2. к товару из списка покупок можно задать количество/вес и когда мы отмечаем его как купленный 
видно что куплено только 2 товара из 3х, доступен словарь товаров
3. к товару из списка покупок можно задать цвет для позиции/строчки, можно добавлять фото
4. заметку/список покупок можно переслать другу/жене в месенджер
5. добавлена интеграция с гугл календарем (напоминания, работа с датой...)
6. заметку со списком дел можно перепланировать/скопировать на другую дату
7. добавлен виджет на главный экран телефона
8. добавлена возможнось "набрать" заметку голосом
9. реализована поддержка Смарт-часов на Android

#### Используемые технологии
Архитектура MVVM, Room, SharedPreferences, Koin, Coroutines, RecyclerView и анимации, Navigation component, retrofit...
