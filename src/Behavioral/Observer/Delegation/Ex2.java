package Behavioral.Observer.Delegation;

import Behavioral.Observer.Delegation.Observers.User;
import Behavioral.Observer.Delegation.Subject.DataBase;

public class Ex2 {

  public static void main(String[] args) {
    DataBase dataBase = new DataBase(2, "School");
    User user1 = new User(1);
    User user2 = new User(2);
    User user3 = new User(3);

    dataBase.eventManager.registerObserver(user1);
    dataBase.eventManager.registerObserver(user2);
    dataBase.eventManager.registerObserver(user3);

    dataBase.setId(123);

    dataBase.setName("PornHub");
  }

}
