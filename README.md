# Devices-Price-Classification
The "Devices" project is a comprehensive solution for managing devices with a Flask API for predicting device price ranges using machine learning. The Spring Boot application provides RESTful APIs to perform CRUD operations on device data, while the Flask API uses machine learning model to predict the price range of a device based on its features.

Project Structure
Spring Boot Application
src/main/java/com/example/devices

Device.java: Entity class representing a device.
DeviceController.java: REST controller for handling device-related requests.
DeviceRepository.java: Repository interface for CRUD operations.
DevicesApplication.java: Main class to bootstrap the Spring Boot application.
ResourceNotFoundException.java: Custom exception for handling resource not found scenarios.
SecurityConfig.java: Security configuration class.
src/main/resources

application.properties: Configuration properties for the application.
src/test/java/com/example/devices

DevicesApplicationTests.java: Unit tests for the application.
target/: Directory containing the compiled output and packaged application.

Flask API
flask_app.py: Flask application script.
device_price_model.pkl: Pre-trained machine learning model.
scaler.pkl: Pre-trained scaler for feature normalization.
requirements.txt: Required Python packages.
Miscellaneous
.gitignore: Specifies files and directories to be ignored by Git.
pom.xml: Maven configuration file.
HELP.md: Additional help or guidance for the project.
.vscode/settings.json: Configuration for VS Code editor.
API Endpoints
Spring Boot Application
Get all devices

URL: /api/devices
Method: GET
Description: Retrieves a list of all devices.
Get a device by ID

URL: /api/devices/{id}
Method: GET
Description: Retrieves a device by its ID.
Create a new device

URL: /api/devices
Method: POST
Description: Creates a new device.
Update a device

URL: /api/devices/{id}
Method: PUT
Description: Updates an existing device by its ID.
Delete a device

URL: /api/devices/{id}
Method: DELETE
Description: Deletes a device by its ID.
Flask API
Predict device price range
URL: /predict
Method: POST
#Description: Predicts the price range of a device based on its features.
Request Body:
{
  "battery_power": 1000,
  "blue": 0,
  "clock_speed": 2.5,
  "dual_sim": 1,
  "fc": 5,
  "four_g": 1,
  "int_memory": 32,
  "m_dep": 0.5,
  "mobile_wt": 150,
  "n_cores": 4,
  "pc": 10,
  "px_height": 800,
  "px_width": 1200,
  "ram": 2048,
  "sc_h": 14,
  "sc_w": 7,
  "talk_time": 10,
  "three_g": 1,
  "touch_screen": 1,
  "wifi": 1
}
Response:
{
  "price_range": 1
}

Machine Learning Details
The machine learning component of the project is implemented in a Jupyter Notebook and a Flask API. Below are the key steps:

1. Load and Visualize the Dataset
Load the dataset using pandas.
Visualize data distributions and relationships using seaborn and matplotlib.
2. Prepare the Dataset
Handle missing values.
Perform feature engineering.
3. Train the Model
Train a RandomForestClassifier.
Evaluate the model using confusion matrix and classification report.
4. Optimize the Model
Perform hyperparameter tuning using GridSearchCV.
Save the trained model and scaler using joblib.
