from selenium import webdriver
import time
driver =webdriver.Chrome()

#运行chrome driver 打开浏览器
driver=webdriver.Chrome()

#设置浏览器窗口大小
driver.set_window_size(1080,800)
#设置全局操作超时时间
driver.implicitly_wait(10)
#打开网址
driver.get('https://kyfw.12306.cn/otn/leftTicket/init')

driver.find_element_by_id("login_user").click()
driver.find_element_by_id("username").send_keys("13129038242")
driver.find_element_by_id("password").send_keys("wbqbornin2012")

print(u"请填写验证码图片，完成后继续...")
input()
driver.find_element_by_link_text("车票预订").click()

#出发地选择
driver.find_element_by_id("fromStationText").click()
driver.find_element_by_css_selector(u"[title=广州]").click()

#目的地选择
driver.find_element_by_id("toStationText").click()
driver.find_element_by_css_selector(u"[title=北京]").click()

#出发日期选择
driver.find_element_by_id("train_date").click()
driver.find_element_by_css_selector("body > div.cal-wrap > div:nth-child(1) > div.cal-cm > div:nth-child(10)").click()

#选择车次
driver.find_element_by_css_selector("#_ul_station_train_code > li:nth-child(1) > label").click()

#查询按钮
driver.find_element_by_id("query_ticket").click()

while True:
    try:
        #点击查询按钮
        driver.find_element_by_id("query_ticket").click()
        e = driver.find_element_by_id("ZY_6c00000G6605")
        e.click()

        if e.text in[u'无','--']:
            print("无票")
            time.sleep(1)
        else:
             print("有票")
             # 预订
             driver.find_element_by_css_selector("#ticket_6c00000G6605 > td.no-br > a").click()
             #乘客信息
             driver.find_element_by_css_selector("#normal_passenger_id > li > label").click()
             #提示
             driver.find_element_by_css_selector("#qd_closeDefaultWarningWindowDialog_id").click()
             #提交订单
             driver.find_element_by_css_selector("#submitOrder_id").click()
            #核对信息
             driver.find_element_by_css_selector("#qr_submit_id").click()
            #确认
             driver.find_element_by_css_selector("#qr_submit_id").click()

    except:
        pass

