package chapter3_sederialization;


public enum SerializerType {
    DefaultJavaSerializer("DefaultJavaSerializer"),
    FastJsonSerializer("FastJsonSerializer"),
    JscksonSerializer("JscksonSerializer"),
    ProtobufSerializer("ProtoBufSerializer"),
    ThriftSerializer("ThriftSerializer"),
    ProtostuffSerializer("ProtostuffSerializer"),
    HessianSerialzer("HessianSerialzer");



    private String type;

    SerializerType(String type) {
        this.type = type;
    }

    public static SerializerType getEnumByType(String type){
        for(SerializerType temp :SerializerType.values()){
            if (type.equals(temp.type)){
                return temp;
            }
        }
        return null;
    }


}
