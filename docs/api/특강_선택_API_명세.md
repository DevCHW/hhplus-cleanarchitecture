# 특강 선택 API

- **URL**: `/api/lectures/available`
- **Method**: `GET`
- **Description**
  - 날짜별로 현재 신청 가능한 특강 목록을 조회합니다.
  - 각 특강은 최대 30명까지만 신청할 수 있습니다.
- **Query Parameters**:
    - `date`: 필수, 조회하려는 특강의 날짜 (예: `2024-12-25`)
    
- **Response**
    ```json
    {
      "result": "SUCCESS",
      "data": [
        {
          "lectureId": 1,
          "title": "허재의 자바 뿌셔버리기",
          "lecturer": "허재",
          "applicationCount": 29,
          "maxCapacity": 30,
          "datetime": "2024-12-25T12:00:00.000000"
        },
        {
          "lectureId": 2,
          "title": "김종협의 코틀린 세상",
          "lecturer": "김종협",
          "applicationCount": 29,
          "maxCapacity": 30,
          "datetime": "2024-12-2512:00:00.000000"
        }
      ] 
    }
    
    ```
