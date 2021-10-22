# Туристическая "социальная сеть"
Система, которая позволяет пользователям выкладывать блог, отзыв или историю о своих путешествиях и делиться этим с остальными

В файле **process-bpmn.svg** приведена BPMN-диаграмма бизнес-процесса

Основной сервис позволяет пользователям выкладывать, обновлять и удалять материал, а также отправлять жалобу на другие публикации

В то же время модератор может управлять списком доступных стран и отелей для публикации, а также проверять часть материалов перед публикацией

При помощи брокера сообщений Kafka реализован асинхронный обмен сообщений о принятии или отказе публикации модератором. 
Также при помощи планировщика Quartz реализована задача, периодически напоминающая модератору о том, сколько непроверенных заявок осталось
