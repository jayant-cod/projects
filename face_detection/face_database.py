import cv2
import os
def ensure_path_exists(path):
    dir=os.path.dirname(path)
    if not os.path.exists(dir):
        os.makedirs(dir)
video=cv2.VideoCapture(0)
video.set(3,640)
video.set(4,480)
face =cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
face_id = 1
count = 0
ensure_path_exists("dataset/")
while True:
    check,frame=video.read()
    gray=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
    faces=face.detectMultiScale(gray,1.1,4)
    for(x,y,w,h) in faces:
        count += 1
        cv2.rectangle(gray,(x,y),(x+w,y+h),(0,255,0),3)
        cv2.imwrite("dataset/User." + str(face_id) +"."+ str(count) + ".jpg", gray[y:y+h,x:x+w])
    cv2.imshow('img',gray) 
    key=cv2.waitKey(1)
    if key==ord('q'):
        break
video.release()
cv2.destroyAllWindows()    