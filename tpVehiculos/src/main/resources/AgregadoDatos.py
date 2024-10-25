import sqlite3
from datetime import datetime, timedelta
import random

# Connect to the database
conn = sqlite3.connect('agencia.db')
cursor = conn.cursor()

# Generate random data
def generate_random_data():
    tipos_documento = ["DNI", "Passport", "ID"]
    nombres = ["Carlos", "Maria", "Lucia"]
    apellidos = ["Perez", "Gomez", "Diaz"]
    documento = random.randint(10000000, 99999999)
    tipo_documento = random.choice(tipos_documento)
    nombre = random.choice(nombres)
    apellido = random.choice(apellidos)
    restringido = random.choice([0, 1])  # BOOLEAN, 0 = False, 1 = True
    nro_licencia = random.randint(1000, 9999)
    fecha_vencimiento = datetime.now() + timedelta(days=random.randint(365, 365*5))
    
    return (tipo_documento, documento, nombre, apellido, restringido, nro_licencia, fecha_vencimiento)

# Insert three rows of random data
for _ in range(5):
    data = generate_random_data()
    cursor.execute('''
        INSERT INTO Interesados (
            TIPO_DOCUMENTO, DOCUMENTO, NOMBRE, APELLIDO, RESTRINGIDO, NRO_LICENCIA, FECHA_VENCIMIENTO_LICENCIA
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    ''', data)

# Commit changes and close the connection
conn.commit()
conn.close()

print("Three rows of random data have been added to the Interesados table.")
