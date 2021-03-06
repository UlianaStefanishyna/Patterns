package Creational.Factorys.Factory_Method.Example.Factorys;

import Creational.Factorys.Factory_Method.Example.Products.Pizza;

public abstract class PizzaFactory {

  public Pizza orderPizza() {
    Pizza pizza;

    pizza = makePizza();

    //Working with product
    pizza.prepare();
    pizza.bake();
    pizza.cut();

    return pizza;
  }

  //Create pizza
  protected abstract Pizza makePizza();

}
