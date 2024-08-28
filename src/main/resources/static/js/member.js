document.addEventListener('DOMContentLoaded', function() {
    // 테이블의 각 행에 클릭 이벤트 추가
    document.querySelectorAll('tr[data-post-seq]').forEach(row => {
        row.addEventListener('click', function() {
            // 클릭된 행에서 데이터 추출
            const postTitle = this.getAttribute('data-post-title');
            const postContent = this.getAttribute('data-post-content');

            // <p> 및 </p> 태그 제거
            const plainTextContent = postContent.replace(/<p[^>]*>|<\/p>/g, "");

            // 모달에 데이터 삽입
            document.getElementById('postTitle').textContent = postTitle;
            document.getElementById('postContent').textContent = plainTextContent;

            // 모달 열기
            document.getElementById('postModal').style.display = 'block';
            document.getElementById('modalOverlay').style.display = 'block';
        });
    });

    // 모달 닫기 핸들러 정의
    function closeModal() {
        document.getElementById('postModal').style.display = 'none';
        document.getElementById('modalOverlay').style.display = 'none';
    }

    // 모달 닫기 이벤트 리스너 추가
    document.getElementById('closeModal').addEventListener('click', closeModal);

    // 오버레이 클릭 시 모달 닫기
    document.getElementById('modalOverlay').addEventListener('click', closeModal);
});