package schoolclass;

public class ClassException extends Exception{
  private String message;
   public ClassException(String message){
       this.message = message;
   }

    @Override
    public String getMessage() {
        return message;
    }
}
