def sayHello() {
    def x = 5;
    println('hello world:' + x);

    "111111";
}

def sayHelloName(name) {
    println('hello,' + name);
    //name;
}

def objectParam(List list) {
    list.add(1);
    list;
}

class User {
    User(age, name) { this.age = age; this.name = name; }
    String name;
    Integer age;
}

def objectParam2(name) {
    def user = new User(1, name)

//使用()
    println(user.name)
    printf("user：%s", user)
    println("$user.name")

//不使用()
    println user
    printf "user：%s", user

}

def encoder(String data) {
    return data.bytes.encodeBase64().toString()
}

encoder(name)

//sayHello();

//sayHelloName(name);

//objectParam(list);

//objectParam2(name)