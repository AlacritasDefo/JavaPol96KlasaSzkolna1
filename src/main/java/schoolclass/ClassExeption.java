package schoolclass;

public class ClassExeption extends Exception{
  private String message;
   public ClassExeption (String message){
       this.message = message;
   }

    @Override
    public String getMessage() {
        return message;
    }
}
