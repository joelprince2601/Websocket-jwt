from fastapi import FastAPI, WebSocket
from jwt_service import decode_jwt, create_token
import asyncio

app = FastAPI()

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    try:
        while True:
            jwt_token = await websocket.receive_text()
            print("\nReceived JWT Token:", jwt_token)
            
            decoded_message = decode_jwt(jwt_token)
            print("Decoded message:", decoded_message)
            
            await websocket.send_text(f"Message received and decoded: {decoded_message}")
            
            # Wait for 10 seconds
            print("\nWaiting for 10 seconds...")
            await asyncio.sleep(10)
            
            # Try to decode the token again after 10 seconds
            print("Checking token after 10 seconds:")
            decoded_message = decode_jwt(jwt_token)
            print("Decoded message after 10 seconds:", decoded_message)
            
            if decoded_message == "Signature has expired":
                print("The token has expired as expected.")
                await websocket.send_text("Token has expired")
            
            print("\nCreating a new token for demonstration:")
            new_token = create_token("New message after expiration")
            print("New token:", new_token)
            decoded_new_message = decode_jwt(new_token)
            print("Decoded new message:", decoded_new_message)
            
    except Exception as e:
        print(f"WebSocket connection closed: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)