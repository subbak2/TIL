# **Gilded Rose Refactoring**

## **Gilded Rose**
  Gilded Rose 는 Allison 이 운영하는 작은 여관입니다. 이 여관은 유명한 도시의 요지에 자리잡고 있습니다. 우리는 이 여관에서 finest goods 를 사고 팝니다. 그런데 상품의 판매 가능 기간(sell by date)이 다가올수록 상품들의 품질은 지속적으로 떨어집니다.

## **프로젝트 세부사항**
 - 모든 아이템에는 판매 가능 기간(sellin)이 있습니다.
    - 판매 가능 기간은 아이템을 팔아야 하는 날까지 남아있는 일수로 표시합니다.
 - 모든 아이템에는 품질(quality)이 있습니다.
    - 품질은 아이템이 얼마나 가치가 있는지를 나타냅니다.
 - 시스템은 매일 자정에 모든 아이템의 값들을 갱신합니다.
 - 판매 가능 기간이 지나면 품질은 두 배씩 빨리 떨어집니다.
 - 아이템의 품질은 음수가 될 수 없습니다.
 - 아이템의 품질은 50보다 클 수 없습니다.
 - “Aged Brie” 는 시간이 지날수록 품질이 증가합니다.
 - “Sulfuras” 는 전설의 아이템입니다.
    - 전설의 아이템은 절대 팔지 않고 품질이 떨어지지 않습니다.
 - “Backstage passes” 는 판매 가능 기간이 다가올수록 품질이 증가합니다.
    - 판매 가능 기간이 10일 이하일 때 품질은 2씩 증가하고, 판매 가능 기간이 5일 이하일 때에는
      3씩 증가합니다.
    - 콘서트가 끝나고 판매 가능 기간이 지나면 품질은 0이 됩니다.

## **Refactoring 실습**
 1. gilded-rose 프로젝트 Clone
 2. 프로젝트 Import
 3. GildedRose 클래스의 updateQuality 메서드 확인
 4. Test Case 작성
    - Test Coverage 100% 달성
 5. Refactoring
    - Conditional Complexity Refactoring
    - Design Pattern 적용
