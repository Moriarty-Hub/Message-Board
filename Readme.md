1. 感觉目前我所掌握的mockito里的测试方法还非常有限，有些我所关心的数据还不知道该怎么测试。比如在BoardServiceTest.java这个文件的testGetAllMessageOrderByCreateTimeDesc()方法中，我只测试了messageRepository.save()这个方法执行了多少次，但是如果在BoardService.java的getAllMessageOrderByCreateTimeDesc()方法中我误把message.setCreator()的参数设为了messageContent，由于类型一致，在编译阶段并不会报错，运行时也不会出错，应该怎么检测出来呢？还是说只能重构源代码才能检测？

    再比如，我在BoardController.java的addMessage方法中给model添加了attribute，我想在测试的时候再对model调用一下getAttribute来检查一下值是否正确，应该怎么办呢？尝试过使用@InjectMocks去自动注入Model，但由于它是一个接口，所以这个办法行不通。（我在IndexControllerTest.java的test2LogIn()中尝试了一种办法，就是在verify中把原来的anystring()替换为我实际期望的值，测试发现可行，但不知道是否符合标准）

2. 在IndexControllerTest.java中的这个位置出现了一个报错，显示是第73行中期望的matcher数量和实际输入的不符，该怎么知道到底需要几个matcher以及应该放在参数中的哪个位置呢？![image-20200405165305184](D:\TDMU\Week 01\MessageBoard\image-20200405165305184.png)

    








