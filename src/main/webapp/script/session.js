var sessionId = sessionStorage.getItem('login_id');

if (sessionId) {
    console.log('세션값이 있습니다:', sessionId);
} else {
    console.log('세션값이 없습니다.');
}
