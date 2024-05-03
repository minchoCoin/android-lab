from keras.preprocessing.image import ImageDataGenerator
import tensorflow as tf

# create generator
data_dir = './malware_images/'
datagen = ImageDataGenerator(rescale=1./255, validation_split=0.2)
# prepare an iterators for each dataset
train_it = datagen.flow_from_directory(data_dir, class_mode='categorical', target_size=(100, 100), batch_size=32)

val_it = validation_generator = datagen.flow_from_directory(
    data_dir, # same directory as training data
    target_size=(100, 100),
    batch_size=32,
    subset='validation')

cnn_model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(32, (3, 3), activation='relu', input_shape=(100, 100, 3)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(9, activation='softmax')])

cnn_model.compile(optimizer='adam',
                  loss='categorical_crossentropy',
                  metrics=['accuracy'])

cnn_model.fit(train_it, epochs=10)
test_eval_result = cnn_model.evaluate(val_it)
print(test_eval_result)

converter = tf.lite.TFLiteConverter.from_keras_model(cnn_model)
tflite_model = converter.convert()

with open('./keras_model_cnn_malware.tflite', 'wb') as f:
  f.write(tflite_model)
