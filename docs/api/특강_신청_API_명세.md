# 특강 신청 API

- **URL**: `/api/lectures/{lectureId}/application`
- **Method**: `POST`
- **Description** 
  - 특정 `userId`가 `lectureId`에 특강을 신청합니다.
  - 선착순으로 최대 정원까지 신청 가능해야 합니다.
  - 이미 신청한 유저는 동일한 특강에 대해서 신청할 수 없습니다.
  - **추가** : 없는 특강으로 신청할 수 없습니다.
- **Request Body**
    ```json
  
    {
      "userId": 1
    }
    ```
- **Response Body**  
  **200 OK**: 신청이 성공적으로 완료되었을 경우.
  ```json
  {
    "result": "SUCCESS",
    "data": {
      "userId": "1",
      "lectureId": "1",
      "message": "특강 신청이 성공적으로 완료되었습니다."
    }
  }
  ```

- **Error**  
  **404 Not Found**: 특강 ID에 해당하는 특강이 없는 경우
  ```json
  {
    "result": "ERROR",
    "error": {
      "code": "LECTURE_NOT_FOUND",
      "message": "존재하지 않는 특강입니다."
    }
  }
  ```

  **400 Bad Request**: 이미 신청한 특강에 대해 다시 신청하려고 했을 경우.
  ```json
  {
    "result": "ERROR",
    "error": {
      "code": "ALREADY_APPLIED",
      "message": "이미 신청한 특강입니다."
    }
  }
  ```

  **409 Conflict**: 특강 신청 정원이 초과된 경우
  ```json
  {
    "result": "ERROR",
    "error": {
      "code": "CAPACITY_EXCEEDED",
      "message": "신청한 특강의 정원이 초과되었습니다."
    }
  }
  ```

