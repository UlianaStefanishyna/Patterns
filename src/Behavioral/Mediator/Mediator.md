# Посредник

![UML](/src/AdditionalDocs/uml/Mediator.png)
(Так же можно выделить Компоненты в отдельный интерфейс)

***Компоненты** — это разнородные объекты, содержащие бизнес-логику программы. Каждый компонент хранит ссылку на объект посредника, но работает с ним только через абстрактный интерфейс посредников. Благодаря этому, компоненты можно повторно использовать в другой программе, связав их с посредником другого типа.

***Посредник** определяет интерфейс для обмена информацией с компонентами. Обычно хватает одного метода для оповещения посредника о событиях, произошедших в компонентах. В параметрах этого метода можно передавать детали события: ссылку на компонент, в котором оно произошло, и любые другие данные.

Позволяет **уменьшить связанность** множества классов между собой, благодаря перемещению этих связей в один класс-посредник, также обеспечивает взаимодействие.

## Применимость
 
 - Когда вам сложно менять некоторые классы из-за **множества хаотичных связей с другими классами**.

   - **Посредник** позволяет **поместить все эти связи в один класс**. После чего вам будет легче их отрефакторить, сделать более понятными и гибкими.

 - Когда вы не можете повторно использовать класс, поскольку он зависит от уймы других классов.

   - После применения паттерна, компоненты теряют прежние связи с другими компонентами. А всё их общение происходит косвенно, через посредника.

 - Когда вам **приходится создавать множество подклассов компонентов, чтобы использовать одни и те же компоненты в разных контекстах**.

   - Если раньше изменение отношений в одном компоненте могли повлечь за собой снежный ком изменений в каждом другом компоненте, то теперь вам **достаточно создать подкласс посредника и поменять в нём связи между компонентами**.

## Шаги реализации

1. Найдите **группу тесно переплетённых классов**, отвязав которые друг от друга, можно получить некоторую пользу. Например, чтобы повторно использовать их код в другой программе.

2. Создайте **общий интерфейс Посредников и опишите в нём методы для взаимодействия с Компонентами**. В простейшем случае достаточно одного метода для получения оповещений от компонентов.

   Этот **интерфейс необходим, если вы хотите повторно использовать классы компонентов для других задач**. В этом случае, всё, что нужно сделать — это создать новый класс конкретного посредника.

3. Реализуйте этот **интерфейс в классе Конкретного посредника**. Поместите в него **поля, которые будут содержать ссылки на все объекты компонентов**.

4. Вы можете пойти дальше и переместить **код создания компонентов в класс Конкретного посредника, превратив его в фабрику** ???.

5. **Компоненты тоже должны иметь ссылку на объект посредника**. Связь между ними удобней всего установить, подавая посредника в параметры конструктора компонентов.

6. Измените **код компонентов так, чтобы они вызывали метод оповещения посредника**, а не методы других компонентов. С другой стороны, **посредник должен вызывать методы нужного компонента, когда получает оповещение**.

## Преимущества и недостатки
 
 | + | - |
 | ------ | ------ |
 |Устраняет зависимости между компонентами, позволяя повторно их использовать.|Посредник может сильно раздуться.
 |Упрощает взаимодействие между компонентами.
 |Централизует управление в одном месте.
 
## Отношения с другими паттернами

- [**Цепочка обязанностей**][Chain_of_Responsibility], [**Команда**][Command], **Посредник** и [**Наблюдатель**][Observer] показывают различные способы работы отправителей запросов с их получателями:

  - [**Цепочка обязанностей**][Chain_of_Responsibility] передаёт запрос последовательно через цепочку потенциальных получателей, ожидая, что какой-то из них обработает запрос.

   - [**Команда**][Command] устанавливает косвенную одностороннюю связь от отправителей к получателям.

   - **Посредник** убирает прямую связь между отправителями и получателями, заставляя их общаться опосредованно, через себя.
   
   - [**Наблюдатель**][Observer] передаёт запрос одновременно всем заинтересованным получателям, но позволяет им динамически подписывать или отписываться от таких оповещений.

- **Посредник** и [**Фасад**][Facade] похожи тем, что пытаются организовать работу множества существующих классов.

  - [**Фасад**][Facade] создаёт упрощённый интерфейс к подсистеме, не внося в неё никакой добавочной функциональности. Сама подсистема не знает о существовании [**Фасада**][Facade]. Классы подсистемы общаются друг с другом напрямую.

  - **Посредник** централизует общение между компонентами системы. Компоненты системы знают только о существовании **Посредника**, у них нет прямого доступа к другим компонентам.

- Разница между **Посредником** и [**Наблюдателем**][Observer] не всегда очевидна. Чаще всего они выступают как конкуренты, но иногда могут работать вместе.
  
  Цель **Посредника** — убрать обоюдные зависимости между компонентами системы. Вместо этого они становятся зависимыми от самого посредника. С другой стороны, цель [**Наблюдателя**][Observer] — обеспечить динамическую одностороннюю связь, в которой одни объекты косвенно зависят от других.

  Довольно популярна реализация **Посредника** при помощи [**Наблюдателя**][Observer]. При этом объект посредника будет выступать издателем, а все остальные компоненты станут подписчиками и смогут динамически следить за событиями, происходящими в посреднике. В этом случае трудно понять, чем же отличаются оба паттерна.

  Но **Посредник** имеет и другие реализации, когда отдельные компоненты жёстко привязаны к объекту посредника. Такой код вряд ли будет напоминать [**Наблюдателя**][Observer], но всё же останется **Посредником**.

  Напротив, в случае реализации посредника с помощью [**Наблюдателя**][Observer], представим такую программу, в которой каждый компонент системы становится издателем. Компоненты могут подписываться друг на друга, в то же время, не привязываясь к конкретным классам. Программа будет состоять из целой сети [**Наблюдателей**][Observer], не имея центрального объекта **Посредника**.