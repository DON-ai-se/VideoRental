# VideoRental

## first commit
 "함수의 단일 책임 원칙(Single Responsibility Principle, SRP)" 위반.

SRP는 소프트웨어 개발에서 중요한 원칙 중 하나입니다. 이 원칙은 "하나의 클래스(혹은 모듈, 함수 등)는 하나의 책임만 가져야 한다"는 것을 말합니다. 이를 따르면 코드를 더욱 쉽게 이해하고, 유지보수하기 쉽게 만들어줍니다.

함수가 두 가지 이상의 역할을 하면, 이를 호출하는 코드에서도 불필요한 복잡성이 증가하고, 의도하지 않은 동작이 발생할 수 있습니다. 

**-> register 메서드는 customer와 video에 대한 처리를 같이 하고 있기 때문에 각각 registerCustomer, registerVideo 메서드로 분리하였음.**

## second & third commit
"Long Method"는 함수나 메서드가 지나치게 긴 것을 말합니다. 

메서드가 너무 길어지면 코드의 가독성이 나빠지며, 유지보수가 어려워집니다. 또한 메서드가 지나치게 길어지면 해당 메서드가 여러 가지 일을 수행하고 있을 가능성이 높습니다.

**-> getReport메서드는 너무 많은 기능을 포함하고 있으며 60줄 정도의 코드 길이를 가지고 있음. second에서는 중복되는 기능을 하나로 통합하였으며 third에서는 ***Extract Method***기법을 사용하여 기능들을 calDaysRented, calEachCharge, calEachPoint, calTotalPoint등의 메서드로 분리하였음.**
