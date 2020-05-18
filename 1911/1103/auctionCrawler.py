import requests
import logging
import pandas as pd
import http.client as http_client
from bs4 import BeautifulSoup


# 대법원 경매사이트에서 경매 매물 데이터를 crawling 하는 코드 샘플
# The only thing missing will be the response.body which is not logged.
# You must initialize logging, otherwise you'll not see debug output.
try:
    import http.client as http_client
except ImportError:
    # Python 2
    import httplib as http_client
http_client.HTTPConnection.debuglevel = 1

logging.basicConfig()
logging.getLogger().setLevel(logging.DEBUG)
requests_log = logging.getLogger("requests.packages.urllib3")
requests_log.setLevel(logging.DEBUG)
requests_log.propagate = True

# 물건상세검색 url, formDataBnd
genReqUrl = 'https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf'
formDataBnd = {'bubwLocGubun' : '1', 'jiwonNm' : '%BC%AD%BF%EF%C1%DF%BE%D3%C1%F6%B9%E6%B9%FD%BF%F8', 'jpDeptCd' : '000000', 'daepyoSidoCd' : '', 'daepyoSiguCd' : '', 'daepyoDongCd' : '', 'notifyLoc' : 'on', 
'rd1Cd' : '', 'rd2Cd' : '', 'realVowel' : '35207_45207', 'rd3Rd4Cd' : '', 'notifyRealRoad' : 'on', 'saYear' : '2020', 'saSer' : '', 'ipchalGbncd' : '000331', 'termStartDt' : '2020.05.10', 'termEndDt' : '2020.05.24', 'lclsUtilCd' : '', 'mclsUtilCd' : '', 'sclsUtilCd' : '', 'gamEvalAmtGuganMin' : '', 'gamEvalAmtGuganMax' : '', 'notifyMinMgakPrcMin' : '', 'notifyMinMgakPrcMax' : '', 'areaGuganMin' : '', 'areaGuganMax' : '', 'yuchalCntGuganMin' : '', 'yuchalCntGuganMax' : '', 'notifyMinMgakPrcRateMin' : '', 'notifyMinMgakPrcRateMax' : '', 'srchJogKindcd' : '', 'mvRealGbncd' : '00031R', 'srnID' : 'PNO102001', '_NAVI_CMD' : '', '_NAVI_SRNID' : '', '_SRCH_SRNID' : 'PNO102001', '_CUR_CMD' : 'InitMulSrch.laf', '_CUR_SRNID' : 'PNO102001', '_NEXT_CMD' : 'RetrieveRealEstMulDetailList.laf', '_NEXT_SRNID' : 'PNO102002', '_PRE_SRNID' : '', '_LOGOUT_CHK' : '', '_FORM_YN' : 'Y'}

inHeaders = {'Host' : 'www.courtauction.go.kr', 'Connection' : 'keep-alive', 'Content-Length' : '771', 'Cache-Control' : 'max-age=0', 'Upgrade-Insecure-Requests' : '1', 'Origin' : 'https://www.courtauction.go.kr', 'Content-Type' : 'application/x-www-form-urlencoded', 'User-Agent' : 'Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/81.0.4044.138Safari/537.36', 'Accept' : 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9', 'Sec-Fetch-Site' : 'same-origin', 'Sec-Fetch-Mode' : 'navigate', 'Sec-Fetch-User' : '?1', 'Sec-Fetch-Dest' : 'frame', 'Referer' : 'https://www.courtauction.go.kr/RetrieveMainInfo.laf', 'Accept-Encoding' : 'gzip,deflate,br', 'Accept-Language' : 'ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7'}

r = requests.post(genReqUrl, data=formDataBnd, headers=inHeaders)

html = r.text
print(html)


# 2) RetrieveRealEstMulDetailList.laf
# 

#soup = BeautifulSoup(r.content, 'lxml-xml')
#print(soup)

# Request URL: https://www.courtauction.go.kr/RetrieveRealEstMulDetailList.laf




# Util 함수 모음

class crawlerUtil:
    # UTF-8을 EUC-KR로 변환
    def utf2euc(self, str):
        return str.encode('euc-kr')
    
    # EUC-KR을 UTF-8로 변환
    def euc2utf(self, str):
        return str.encode('utf-8')

    # Header Parser
    def headerParser(self, str):
       
        outString = ""
        tmpString = "'"

        for i in str:
            if i==':':
                outString = outString + tmpString + "' : "
                tmpString = "'"

            elif i=='\n':
                outString = outString + tmpString + "', "
                tmpString="'"

            elif i!=' ':
                tmpString=tmpString+i
        return outString


    # formData Parser
    def formDataParser(self, str):
        
        outString = ""
        tmpString = "'"

        for i in str:
            if i=='=':
                outString = outString + tmpString + "' : "
                tmpString = "'"

            elif i=='&':
                outString = outString + tmpString + "', "
                tmpString="'"

            else:
                tmpString=tmpString+i
        return outString
