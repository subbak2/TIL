import requests
import logging
import http.client as http_client
import json

try:
    import http.client as http_client
except ImportError:
    # Python 2
    import httplib as http_client
http_client.HTTPConnection.debuglevel = 0

# 로그용
# logging.basicConfig()
# logging.getLogger().setLevel(logging.DEBUG)
# requests_log = logging.getLogger("requests.packages.urllib3")
# requests_log.setLevel(logging.DEBUG)
# requests_log.propagate = True

authKey = '614f47717673756239364a68427852'

# URL : http://openapi.seoul.go.kr:8088/{인증키}/json/bikeList/1/5/
genReqUrl = 'http://openapi.seoul.go.kr:8088/'+authKey+'/json/bikeList/1/1000/'

data = requests.get(genReqUrl)
result = json.loads(data.text)

# print(result)

rows = result["rentBikeStatus"]["row"]

for i in rows:
    stationName = i["stationName"]
    rackTotCnt = i["rackTotCnt"]
    parkingBikeTotCnt = i["parkingBikeTotCnt"]

    print("대여소 이름 : "+stationName+", 거치대 개수 : "+rackTotCnt+", 주차된 자전거 수 : "+parkingBikeTotCnt)