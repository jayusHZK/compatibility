def sayHello() {
    def x = 5;
    println('hello world:' + x);

    "111111";
}

def sayHelloName(name){
    println('hello,' + name);
    name;
}

def objectParam(List list){
    list.add(1);
    list;
}


//sayHello();

//sayHelloName(name);

objectParam(list);
