1. 感觉目前我所掌握的mockito里的测试方法还非常有限，有些我所关心的数据还不知道该怎么测试。比如在BoardServiceTest.java这个文件的
   testGetAllMessageOrderByCreateTimeDesc()方法中，我只测试了messageRepository.save()这个方法执行了多少次，但是如果在BoardService.java
   的getAllMessageOrderByCreateTimeDesc()方法中我误把message.setCreator()的参数设为了messageContent，由于类型一致，在编译阶段并不会报错，运行时也不会出错，
   应该怎么检测出来呢？还是说只能重构源代码才能检测？
   
   answer：
   1、应该是addMessage方法的测试吧！你写成了testGetAllMessageOrderByCreateTimeDesc，已在BoardServiceTest#testAddMessage中给你加入如何测试的代码
   2、测试的方法名、参数命名不规范，IndexServiceTest中的方法名test1VerifyUser、test2VerifyUser；IndexController#register方法的参数password1、password2
   3、关于mockito的测试，网上也有很多资料，遇到不会写的或者没接触过的写法，但是自己心里有疑问的，可以上网查
   

2 再比如，我在BoardController.java的addMessage方法中给model添加了attribute，我想在测试的时候再对model调用一下getAttribute来检查一下值是否正确，应该怎么办呢？
  尝试过使用@InjectMocks去自动注入Model，但由于它是一个接口，所以这个办法行不通。（我在IndexControllerTest.java的test2LogIn()中尝试了一种办法，
  就是在verify中把原来的anystring()替换为我实际期望的值，测试发现可行，但不知道是否符合标准）

  answer：
  1、可以的，只要是验证了你的预期值和最终的实际值是一致的就是OK的，anystring()一般用于不是太关心具体值的时候为了方便可以可以这样写，最严谨的就是
     你现在的这种写法，就是在verify中把原来的anyString()替换为我实际期望的值

3. 在IndexControllerTest.java中的这个位置出现了一个报错，显示是第73行中期望的matcher数量和实际输入的不符，该怎么知道到底需要几个matcher以及应该放在参数中的哪个位置呢？
    ![image](https://github.com/Moriarty-Hub/Message-Board/blob/master/image-20200405165305184.png)
    answer：
    1、在方法调用的时候是需要传"具体的参数"的值的，方法调用的时候是正常的程序调用，不属于Mockito的，不能使用any()、anyString()之类的，程序不认识
     any()、anyString()这些是搭配Mockito的方法用的，一般用在when、verify、spy等等上的，所以你那样使用自然会报错，多写，多了解就熟悉了

​    

3. 在Spring Boot里配置数据库的时候，我们是将数据库的URL，账号密码直接以明文的形式添加到application.properties里面的，在企业里进行开发的时候我们也是这样做的吗？感觉似乎有点不安全