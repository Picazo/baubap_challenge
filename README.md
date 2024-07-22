# Code Challenge Baubap

* Language: Kotlin
* Clean Architecture
* Unit Testing: View Model and Use Case
* Instrumentation Testing
* Jetpack Compose

## App structure:

* Hilt was used as a dependency injector and is implemented with a separate module for better concern handling
* For networking Retrofit was used respectively
* To handle asynchronization, kotlin coroutines were used
* Finally, to carry out the unit tests, MockK was used

For the consumption of services, a Repository pattern was used that goes very well with Clean, as an extra, a SplashScreen was used using the Google library in which the use of a direct class is no longer necessary for its implementation.

## Login

The Login screen was built by making a mock screen of what a user can normally find inside the screen, we left elements that do not have any action such as the case of 

* Forget password 
* Sign up

These were only left as a reference, 

We proposed the response of a service call using mock.io, in which the following object is answered:

{
  “status": ‘success’,
  “message": ‘Login successful’,
  “token": ‘eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9’,
  “userId": 1
}

This is to be able to demonstrate the knowledge in implementing a clean architecture with MVVM and have a good repository design pattern.

Also the implementation of unit tests and instrumented tests is performed.

## Demo

https://github.com/user-attachments/assets/0a3abf2e-124d-4d1c-97f4-43fda63dd951


## Unit Test

<img width="1053" alt="UnitTestBaubapChallenge" src="https://github.com/user-attachments/assets/e5cf9706-d0b8-4d88-860b-f883005c08fa">
<img width="856" alt="ImplementationTestBaubapChallenge" src="https://github.com/user-attachments/assets/23a526f3-c076-4a76-989a-93638dc0cc57">








