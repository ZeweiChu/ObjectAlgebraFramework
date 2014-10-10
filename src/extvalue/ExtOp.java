package extvalue;

public class ExtOp {

}

interface A {}

interface B extends A {}

abstract class C {abstract A m();}

class D {B m() {return new B(){};}}
