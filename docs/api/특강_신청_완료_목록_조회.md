# 특강 신청 완료 목록 조회 API

- **URL**: `/api/lectures/applied`
- **Method**: `GET`
- **Description**
  - 특정 `userId`로 신청 완료된 특강 목록을 조회합니다. 
  - 각 항목에는 특강 ID, 이름, 강연자 정보 등이 포함됩니다.
- **Query Parameters**:
  - `userId`: 필수, 유저 ID
- **Response Body**
    ```json
    {
      "result": "SUCCESS",
      "data": [
        {
          "lectureId": 1,
          "title": "이펙티브 자바 톺아보기",
          "lecturer": "홍길동",
          "datetime": "2024-12-28T12:00:00.000000"
        },
        {
          "lectureId": "2",
          "title": "코틀린 딥다이브",
          "lecturer": "아무개",
          "datetime": "2025-01-04T12:00:00.000000"
        }
      ]
    }
    ```