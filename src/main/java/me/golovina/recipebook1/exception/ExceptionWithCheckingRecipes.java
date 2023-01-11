package me.golovina.recipebook1.exception;

public class ExceptionWithCheckingRecipes extends Exception{
    public ExceptionWithCheckingRecipes(){

    }
    public ExceptionWithCheckingRecipes (String message){
        super(message);
    }
}
