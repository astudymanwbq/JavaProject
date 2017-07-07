#coding=utf-8
import urllib

from appium import webdriver
from time import sleep
import json
import unicodedata

from pip._vendor.appdirs import unicode


class WeChat:
    def __init__(self):
        self.desired_caps = {}
        self.desired_caps['platformName'] = 'Android'
        self.desired_caps['platformVersion'] = '4.4.2'
        self.desired_caps['deviceName'] = 'Android Emulator'
        self.desired_caps['appPackage'] = 'com.tencent.mm'
        self.desired_caps['appActivity'] = 'ui.LauncherUI'
        self.desired_caps['unicodeKeyboard'] = True
        self.desired_caps['resetKeyboard'] = True

        # 打开app
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', self.desired_caps)
        sleep(6)

        # 获得机器屏幕大小x,y
    def getSize(self):
        x = self.driver.get_window_size()['width']
        y = self.driver.get_window_size()['height']
        sleep(2)
        return(x,y)

        # 屏幕向右滑动
    def swipRight(self,t):
        l = self.getSize()
        x1 = int(l[0] * 0.75)
        y1 = int(l[1] * 0.5)
        x2 = int(l[0] * 0.25)
        self.driver.swipe(x1, y1, x2, y1, t)

        # 向左滑动
    def swipeLeft(self, t):
        l = self.getSize()
        x1 = int(l[0] * 0.25)
        y1 = int(l[1] * 0.5)
        x2 = int(l[0] * 0.75)
        self.driver.swipe(x1, y1, x2, y1, t)

        # 向上滑动

    def swipeUp(self, t):
        l = self.getSize()
        x1 = int(l[0] * 0.5)
        y1 = int(l[1] * 0.25)
        y2 = int(l[1] * 0.75)
        self.driver.swipe(x1, y2, x1, y1, t)

        # 向下滑动
    def swipeDown(self, t):
        l = self.getSize()
        x1 = int(l[0] * 0.5)
        y1 = int(l[1] * 0.25)
        y2 = int(l[1] * 0.75)
        self.driver.swipe(x1, y1, x1, y2, t)

    def login(self):
        self.driver.find_element_by_id("com.tencent.mm:id/cdi").click()
        sleep(3)
        username=self.driver.find_element_by_id("com.tencent.mm:id/brm")
        username.send_keys("13129038242")
        password=self.driver.find_element_by_id("com.tencent.mm:id/gr")
        password.send_keys("wbq1994..")
        #登录
        login=self.driver.find_element_by_id("com.tencent.mm:id/aax")
        login.click()
        sleep(5)

    def addfriend(self):
        self.driver.find_element_by_id("com.tencent.mm:id/f_").click()
        addfriend=self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"添加朋友\")")
        addfriend.click()
        self.driver.find_element_by_id("com.tencent.mm:id/gr").click()
        #输入账号
        account=self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"搜索\")")
        account.send_keys("15622110487")
        #搜索
        self.driver.find_element_by_id("com.tencent.mm:id/ho").click()
        sleep(3)
        #添加到通讯录
        add=self.driver.find_element_by_id("com.tencent.mm:id/adi")
        add.click()
        sleep(3)
        #发送
        send=self.driver.find_element_by_id("com.tencent.mm:id/gd")
        send.click()

    def openFriend(self):
        friendCircle=self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"朋友圈\")")
        friendCircle.click()
        sleep(2)
    #点赞
    def zang(self):
            common=self.driver.find_elements_by_id("com.tencent.mm:id/clj")
            sleep(2)
            print(len(common))
            # #遍历点开评论按钮 点赞
            for a in range(0,len(common)-1):
                 common[a].click()
                 givegood=self.driver.find_element_by_id("com.tencent.mm:id/ckn")
                 if givegood.text=="赞":
                     givegood.click()

    def specialFriend(self):
            name=self.driver.find_elements_by_id("com.tencent.mm:id/afv")
            for i in range(0,len(name)):
                    #找到指定好友
                    # 点开评论按钮
                    if name[i] == "Ashe":
                        common = self.driver.find_elements_by_id("com.tencent.mm:id/clj")
                        sleep(2)
                        print(len(common))
                        # #遍历点开评论按钮 点赞
                        for a in range(0, len(common) - 1):
                            common[a].click()
                            givegood = self.driver.find_element_by_id("com.tencent.mm:id/ckn")
                            if givegood.text == "赞":
                                givegood.click()

    def autoChatTest(self):
        # 选择指定好友，进入聊天界面
        self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"大哥  \")").click()

        sleep(1)
        # 向上滑动,获取最新消息
        self.swipeUp(2000)
        sleep(3)

        # 捕捉所有聊天文本信息,返回数组
        sourceList = self.driver.find_elements_by_id("com.tencent.mm:id/if")
        #获取最新的聊天信息
        pre = sourceList[len(sourceList) - 1].text
        #打印最新的聊天信息
        print(pre)

        sleep(3)
        print("开始聊天:")
        # 自动聊天

        # 怎么判断别人向你发消息：如果当前len(sourceList)>l,就说明聊天信息多了
        # 即别人向你发消息
        while True:
            # 因为存在更新消息,所以要时刻滑动
            self.swipeUp(3000)
            sleep(2)
            # 更新sourceList
            sourceList = wechat.driver.find_elements_by_id("com.tencent.mm:id/if")

            if pre != sourceList[len(sourceList) - 1].text:
                sourceText = sourceList[len(sourceList) - 1].text
                print("大哥  : ")
                print(sourceText)

                pre = sourceText
                reply = self.autoChat(str(unicode(sourceText)))
                # 输入消息

                self.driver.find_element_by_id("com.tencent.mm:id/a3b").click()
                self.driver.find_element_by_id("com.tencent.mm:id/a3b").send_keys(str(reply))
                sleep(2)
                # 发送
                self.driver.find_element_by_id("com.tencent.mm:id/a3h").click()
                print( "robot: ")
                print(reply)
                pre = reply
                if sourceText == "再见":
                    break

                sourceText = ""
                self.swipeUp(3000)
                sleep(2)

    # 先通过网络请求获取到json串
    def getjson(self, url):
        data = urllib.request.urlopen(url=url).read()
        return json.loads(data)

    # 微信自动化聊天,这里调用了青云客智能聊天机器人API(http://api.qingyunke.com/)
    # 根据输入自动回应
    def autoChat(self, sourceText):
        # 对于中文，在进行网络请求的时候需要转换成安全的编码，借助quote方法即可
        robot_api = 'http://api.qingyunke.com/api.php?key=free&appid=0&msg={}'.format(urllib.request.quote(sourceText))
        data = self.getjson(url=robot_api)
        # 返回API回应内容
        return data['content']






        #com.tencent.mm:id/clj 返回评论数组 common数组
        # common=self.driver.find_elements_by_id("com.tencent.mm:id/clj")
        # sleep(2)
        # print(len(common))
        # #遍历点开评论按钮 点赞
        # for a in range(0,len(common)):
        #     common[a].click()
        #     givegood=self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"赞\")")
        #     givegood.click()



        # common[0].click()
        # #点赞
        # givegood=self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"赞\")")
        # givegood.click()



        # #点赞
        # zang = self.driver.find_element_by_android_uiautomator("new UiSelector().text(\"赞\")")




if __name__ == '__main__':
    wechat = WeChat()
    #登录
    #wechat.login()
    #添加好友
    #wechat.addfriend()
    #向右滑动 滑到朋友圈
    # sleep(3)
    # wechat.swipRight(1000)
    # sleep(2)
    # wechat.swipRight(1000)
    # sleep(2)
    # #打开朋友圈
    # wechat.openFriend()
    #打开评论按钮的第一个元素
    # wechat.zang()
    #批量点赞
    # for i in range(0,10):
    #     wechat.zang()
    #     wechat.swipeUp(1000)
    #     sleep(3)
    sleep(3)
    wechat.autoChatTest()






    #
    # wechat.swipeUp(1000)
    #
    # wechat.swipeDown(1000)
    #
    # wechat.swipeLeft(1000)
