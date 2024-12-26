# 공통 API 응답 구조 명세
> 이 문서는 어플리케이션 내부 공통 API 응답 구조를 정의합니다.

## 요청 성공 시
### Example
```json
{
  "result": "SUCCESS",
  "data": {
    "example" : "Hello World!"
  } 
}
```
### Description

|**필드명**|**타입**| **설명**                         |
|--|--|--|
| result |String| API 결과(SUCCESS:성공 / ERROR:실패)  |
| data |Object/Array| 응답 데이터                         |

## 에러 발생 시
### Example
```json
{
  "result": "ERROR",
  "error": {
    "code": "SERVER_ERROR",
    "message": "서버 에러가 발생했습니다."
  }
}
```
| **필드명** | **타입** | **설명**                        |
|---------|--------|-------------------------------|
| result  | String | API 결과(SUCCESS:성공 / ERROR:실패) |
| error   | Object | 에러 응답 정보                      |
| error.code | String | 에러 코드                         |
| error.message | String | 에러 메세지                        |
