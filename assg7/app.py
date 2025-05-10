from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/add', methods=['GET'])
def add_numbers():
    try:
        a = int(request.args.get('a'))
        b = int(request.args.get('b'))
        return jsonify({'result': a + b})
    except (TypeError, ValueError):
        return jsonify({'error': 'Invalid input'}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
