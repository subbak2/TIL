from flask import Flask, render_template, request, jsonify
from pymongo import MongoClient


client = MongoClient('localhost',27017)
db = client.dbCrawl

app = Flask(__name__)

@app.route('/')
def home():
    return 'This is home'

@app.route('/mypage')
def mypage():
    return 'This is mypage'

@app.route('/test', methods=['POST'])
def test_post():
    rank_receive = request.form['rank_give']
    rank_receive = int(rank_receive)
    artist_receive = request.form['artist_give']

    db.GENIECHART001.update_one({'rank':rank_receive},{'$set':{'artist':artist_receive}})

    return jsonify({'result':'success'})

@app.route('/test', methods=['GET'])
def test_get():
    rank_receive = request.args.get('rank_give')
    rank_receive = int(rank_receive)
    print(rank_receive)
    music_info = db.GENIECHART001.find_one({'rank':rank_receive}, {'_id':0})
    return jsonify({'result':'success', 'info':music_info})

@app.route('/new', methods=['POST'])
def insert_movie():
    rank = int(request.form['rank_give'])
    title = request.form['title_give']
    artist = request.form['artist_give']

    doc = {
        'rank':rank,
        'title':title,
        'artist':artist
    }

    db.GENIECHART001.insert_one(doc)

    return jsonify({'result':'success'})


if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)
