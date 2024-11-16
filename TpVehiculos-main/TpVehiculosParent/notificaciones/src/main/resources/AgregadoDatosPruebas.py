import sqlite3
from datetime import datetime, timedelta

# Conectar a la base de datos (o crearla si no existe)
conn = sqlite3.connect('agencia.db')
cursor = conn.cursor()

# Datos de ejemplo
datos_pruebas = [
    (1, 101, 201, 301, datetime.now(), datetime.now() + timedelta(hours=1), "Prueba 1"),
    (2, 102, 202, 302, datetime.now(), datetime.now() + timedelta(hours=2), "Prueba 2"),
    (3, 103, 203, 303, datetime.now(), datetime.now() + timedelta(hours=3), "Prueba 3"),
    (4, 104, 204, 304, datetime.now(), datetime.now() + timedelta(hours=4), "Prueba 4"),
    (5, 105, 205, 305, datetime.now(), datetime.now() + timedelta(hours=5), "Prueba 5"),
]

# Insertar datos en la tabla Pruebas
insert_query = '''
INSERT INTO Pruebas (ID, ID_VEHICULO, ID_INTERESADO, ID_EMPLEADO, FECHA_HORA_INICIO, FECHA_HORA_FIN, COMENTARIOS)
VALUES (?, ?, ?, ?, ?, ?, ?)
'''

cursor.executemany(insert_query, datos_pruebas)

# Confirmar cambios y cerrar la conexión
conn.commit()
conn.close()

print("Tabla 'Pruebas' poblada con ejemplos.")
