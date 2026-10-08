import java.util.HashMap;

public class Wall {
    private Boolean solid;
    private String inspect;
    private static HashMap<Integer, Wall> wallHash= new HashMap<>();

    public Wall(String i, Boolean s){
      solid = s;
      inspect = i;
    }

    public Wall(){}

    public void genWallHash(){
        wallHash.put(0, new Wall("There is nothing there",false));
        wallHash.put(1, new Wall("It's a solid wall",true));
    }

    public Wall(int id){
        if(wallHash.containsKey(id)){
        Wall i = wallHash.get(id);
        inspect = i.getInspect();
        solid = i.getSolid();
       }else{
        System.out.print("Unkown Item ID:"+id);
       }
    }


    public void invertSolid(){
      this.solid=!solid;
    }
    
    public String getInspect(){
      return inspect;
    }

    public Boolean getSolid() {
        return solid;
    }

    public void setInspect(String i){
      inspect=i;
    }

}