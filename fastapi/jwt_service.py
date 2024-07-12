import jwt
from datetime import datetime, timezone, timedelta
import logging

SECRET_KEY = "secret_test"

def create_token(message: str) -> str:
    payload = {
        "sub": message,
        "exp": datetime.now(tz=timezone.utc) + timedelta(seconds=20)
    }
    return jwt.encode(payload, SECRET_KEY, algorithm="HS256")

def decode_jwt(token: str) -> str:
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        logging.info(f"Successfully decoded payload: {payload}")
        return payload["sub"]
    except jwt.ExpiredSignatureError:
        logging.error("Signature has expired")
        return "Signature has expired"
    except jwt.InvalidTokenError as e:
        logging.error(f"Invalid token: {e}")
        return "Invalid token"