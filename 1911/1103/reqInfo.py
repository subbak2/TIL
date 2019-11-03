import requests
from bs4 import BeautifulSoup
from pymongo import MongoClient
from datetime import datetime

"""
0. mongoDB와 연결한다.
1. 오늘 날짜에 해당하는 음원 순위를 가져온다.
    1-1. 오늘 날짜를 구한다.
    1-2. 오늘 날짜에 해당하는 음원 순위를 가져온다.
    1-3. select 를 이용하여 순위별 곡명, 가수명을 구한다.
    1-4. 해당 정보를 이용하여 dictionary를 만들고, 이것을 mongoDB에 저장한다.    
2. 순위, 곡명, 가수를 genie Collection에 저장한다.
"""

client = MongoClient('localhost', 27017)
db = client.dbCrawl

today = datetime.today().strftime('%Y%m%d')

headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
url = 'https://www.genie.co.kr/chart/top200?ditc=D&rtm=N&ymd={}'.format(today)

# 1-2. 오늘 날짜에 해당하는 음원 순위를 가져온다.
resp = requests.get(url, headers=headers)

soup = BeautifulSoup(resp.text, 'lxml')
music_info = soup.select('td.info')

#print(music_info)

rank = 0 
for music in music_info:
    title = music.select('a.title.ellipsis')[0].text.strip()
    artist = music.select('a.artist.ellipsis')[0].text.strip()
    rank+=1
    print(str(rank)+" "+title+" "+artist)

     # 1-4. 해당 정보를 이용하여 dictionary를 만들고, 이것을 mongoDB에 저장한다.
    doc = {
        'rank': rank,
        'title': title,
        'artist': artist
    }
    db.GENIECHART001.insert_one(doc)