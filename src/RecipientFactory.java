import java.util.List;

public class RecipientFactory {
    public Recipient getRecipient(String recipientType, List<String> dataList){
        switch (recipientType) {
            case ("official"):
                return new Official(dataList.get(0), dataList.get(1), dataList.get(2));
            case ("office_friend"):
                return new OfficeFriend(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3));

            case ("personal"):
                return new Personal(dataList.get(0), dataList.get(2), dataList.get(1), dataList.get(3));
            default:
                return null;
        }
    }
}


//public class ShapeFactory {
//
//    //use getShape method to get object of type shape
//    public Shape getShape(String shapeType){
//        if(shapeType == null){
//            return null;
//        }
//        if(shapeType.equalsIgnoreCase("CIRCLE")){
//            return new Circle();
//
//        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
//            return new Rectangle();
//
//        } else if(shapeType.equalsIgnoreCase("SQUARE")){
//            return new Square();
//        }
//
//        return null;
//    }
//}