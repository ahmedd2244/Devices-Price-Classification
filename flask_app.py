from flask import Flask, request, jsonify
import pandas as pd
import joblib

# Load the model
model = joblib.load('device_price_model.pkl')
scaler = joblib.load('scaler.pkl')

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    features = pd.DataFrame([data], columns=[
        'battery_power', 'blue', 'clock_speed', 'dual_sim', 'fc', 'four_g', 
        'int_memory', 'm_dep', 'mobile_wt', 'n_cores', 'pc', 'px_height', 
        'px_width', 'ram', 'sc_h', 'sc_w', 'talk_time', 'three_g', 
        'touch_screen', 'wifi'
    ])
    scaled_features = scaler.transform(features)
    prediction = model.predict(scaled_features)[0]
    return jsonify({'price_range': int(prediction)})

if __name__ == '__main__':
    app.run(debug=True)