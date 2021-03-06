# Итератор

![UML](/src/AdditionalDocs/uml/Iterator.png)
 
Даёт возможность **последовательно обходить элементы составных объектов**, не раскрывая их внутреннего представления.

Коллекции (Iterable collection) не всегда являются списком. Это может быть и база данных, и удалённое API, и даже дерево [**Компоновщика**][Composite]. Поэтому сама коллекция может создавать итераторы, так как она знает, какие именно итераторы могут с ней работать.

**JDK имеет интерфейсы [Iterable\<T>](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html), [Iterator\<E>](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)**

**Так же JDK есть более функциональный [ListIterator\<E>](https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html), используется классами, реализующими интерфейс [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)**

## Применимость
 - Когда у вас есть сложная структура данных, и вы хотите скрыть от клиента детали её реализации (из-за сложности или вопросов безопасности).

   - **Итератор предоставляет клиенту всего несколько простых методов перебора элементов коллекции**. Это не только упрощает доступ к коллекции, но и защищает её данные от неосторожных или злоумышленных действий.

 - Когда вам нужно иметь **несколько вариантов обхода** одной и той же структуры данных.

   - Нетривиальные алгоритмы обхода структуры данных могут иметь довольно объёмный код. Этот код будет захламлять всё вокруг, если поместить его в класс коллекции или где-то посреди основной бизнес-логики программы. Применив итератор, вы можете **переместить код обхода структуры данных в собственный класс, упростив поддержку остального кода**.

 - Когда вам хочется иметь **единый интерфейс обхода различных структур данных**.

   - **Итератор позволяет вынести реализации различных вариантов обхода в подклассы**. Это позволит легко взаимозаменять объекты итераторов, в зависимости от того, с какой структурой данных приходится работать.

## Шаги реализации
 
1. Создайте **интерфейс итераторов**. В качестве минимума, вам понадобится **операция получения следующего элемента**. Но для удобства можно предусмотреть и другие методы, например, для **получения предыдущего элемента**, **текущей позиции**, **проверки окончания обхода и прочих**.

2. Создайте интерфейс коллекции и опишите в нём **метод получения итератора**. Важно, чтобы его сигнатура **возвращала общий интерфейс итераторов**, а не один из конкретных итераторов.

3. Создайте классы конкретных итераторов для тех коллекций, которые нужно обходить с помощью паттерна. **Итератор должен быть привязан только к одному объекту коллекции. Обычно эта связь устанавливается через конструктор**.

4. Реализуйте методы получения итератора в конкретных классах коллекций. Они должны создавать новый итератор того класса, который способен работать с данным типом коллекции. **Коллекция должна передавать собственную ссылку в созданный итератор**.

5. **В клиентском коде и в классах коллекций не должно остаться кода обхода элементов**. Клиент должен получать новый итератор из объекта коллекции каждый раз, когда ему нужно перебрать её элементы.

 ## Преимущества и недостатки
 
 | + | - |
 | ------ | ------ |
 |Упрощает классы хранения данных.|Неоправдан, если можно обойтись простым циклом.
 |Позволяет реализовать различные **способы обхода структуры данных**.
 |Позволяет одновременно перемещаться по структуре данных в разные стороны.
 
 
## Отношения с другими паттернами

- Вы можете обходить дерево **[**Компоновщика**][Composite]**, используя **Итератор**.

- [**Фабричный метод**][Factory_method] можно использовать вместе с **Итератором**, чтобы подклассы коллекций могли создавать подходящие им итераторы.

- [**Снимок**][Memento] можно использовать вместе с **Итератором**, чтобы сохранить текущее состояние обхода структуры данных и вернуться к нему в будущем, если потребуется.

- [**Посетитель**][Visitor] можно использовать совместно с **Итератором**. **Итератор** будет отвечать за обход структуры данных, а [**Посетитель**][Visitor] - за выполнение действий над каждым её компонентом.


[Abstract_Factory]: </src/Creational/Factorys/Abstract_Factory/Abstract_Factory.md>
[Factory_Method]: </src/Creational/Factorys/Factory_Method/Factory_Method.md>
[Builder]: </src/Creational/Builder/Builder.md>
[Prototype]: </src/Creational/Prototype/Prototype.md>
[Singleton]: </src/Creational/Singleton/Singleton.md>

[Adapter]: </src/Structural/Adapter/Adapter.md>
[Bridge]: </src/Structural/Bridge/Bridge.md>
[Composite]: </src/Structural/Composite/Composite.md>
[Decorator]: </src/Structural/Decorator/Decorator.md>
[Facade]: </src/Structural/Facade/Facade.md>
[Flyweight]: </src/Structural/Flyweight/Flyweight.md>
[Proxy]: </src/Structural/Proxy/Proxy.md>

[Chain_of_Responsibility]: </src/Behavioral/Chain_of_Responsibility/Chain_of_Responsibility.md>
[Command]: </src/Behavioral/Command/Command.md>
[Iterator]: </src/Behavioral/Iterator/Iterator.md>
[Mediator]: </src/Behavioral/Mediator/Mediator.md>
[Memento]: </src/Behavioral/Memento/Memento.md>
[Observer]: </src/Behavioral/Observer/Observer.md>
[State]: </src/Behavioral/State/State.md>
[Strategy]: </src/Behavioral/Strategy/Strategy.md>
[Template_Method]: </src/Behavioral/Template_Method/Template_Method.md>
[Visitor]: </src/Behavioral/Visitor/Visitor.md>