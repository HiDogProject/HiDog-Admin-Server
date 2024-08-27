function showPostDetails(postSeq) {
    const url = `http://localhost:3000/app/board/view/${postSeq}`;
    window.open(url, '_blank', 'width=800,height=600');
}

// 모든 트리거에 클릭 이벤트를 추가하는 코드
document.addEventListener('DOMContentLoaded', function() {
    const rows = document.querySelectorAll('tr[data-post-seq]');
    rows.forEach(row => {
        row.addEventListener('click', function() {
            const postSeq = this.getAttribute('data-post-seq');
            showPostDetails(postSeq);
        });
    });
});