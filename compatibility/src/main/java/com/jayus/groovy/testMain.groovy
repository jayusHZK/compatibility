
class testMain {

    static void main(String[] args) {
        print('groovy hello wrold')
    }

    private static List test(List list){
        list.add(1);
        return list;
    }

    byte[] decode(byte[] bytes){
        return Base64.decoder.decode(bytes)
    }

}
