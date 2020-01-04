import cv2
recognizer=cv2.face.LBPHFaceRecognizer_create()
recognizer.read('trainer/trainer.yml')
faceCascade=cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
font = cv2.FONT_HERSHEY_SIMPLEX
cam = cv2.VideoCapture(0)
while True:
    ret, im =cam.read()
    gray = cv2.cvtColor(im,cv2.COLOR_BGR2GRAY)
    faces = faceCascade.detectMultiScale(gray, 1.2,5)
    for(x,y,w,h) in faces:
        cv2.rectangle(im, (x-20,y-20), (x+w+20,y+h+20),(0,255,0), 4)
        Id, confidence = recognizer.predict(gray[y:y+h,x:x+w])
        print(Id)
        if(Id == 1):
            Id = "Jayant {0:.2f}%".format(round(100 - confidence, 2))
            cv2.rectangle(im, (x-22,y-90), (x+w+22, y-22), (0,255,0), -1)
            cv2.putText(im, str(Id), (x,y-40), font, 1, (255,255,255), 3)
        else:
            Id = "unknown {0:.2f}%".format(round(100 - confidence, 2))
            cv2.rectangle(im, (x-22,y-90), (x+w+22, y-22), (0,255,0), -1)
            cv2.putText(im, str(Id), (x,y-40), font, 1, (255,255,255), 3)
    cv2.imshow('im',im)
    key=cv2.waitKey(1)
    if key==ord('q'):
        break
cam.release()
cv2.destroyAllWindows()            