from selenium import webdriver
from selenium.webdriver.chrome.options import Options  
from bs4 import BeautifulSoup


options = Options()
options.headless = True
browser = webdriver.Chrome(r'C:\Github\TIL\1911\1117\chromedriver.exe', options=options)
browser.get('http://seleniumhq.org/')
print(browser.title)
browser.close()

# browser = webdriver.Chrome(r'C:\Github\TIL\1911\1117\chromedriver.exe')
# browser.get('http://naver.com')
# search_input = browser.find_element_by_css_selector('.input_text')
# search_button = browser.find_element_by_css_selector('#search_btn')
# search_input.clear()
# search_input.send_keys("삼성SDS")
# search_button.click()
