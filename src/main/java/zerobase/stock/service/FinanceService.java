package zerobase.stock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.stock.model.Company;
import zerobase.stock.model.Dividend;
import zerobase.stock.model.ScrapedResult;
import zerobase.stock.persist.CompanyRepository;
import zerobase.stock.persist.DividendRepository;
import zerobase.stock.persist.entity.CompanyEntity;
import zerobase.stock.persist.entity.DividendEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));
        // 2. 조회된 회사 ID로 배당금을 조회
        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        // 3. 결과 조합 후 반환
        List<Dividend> dividends = new ArrayList<>();
        for (var entity : dividendEntities) {
            dividends.add(Dividend.builder()
                            .date(entity.getDate())
                            .dividend(entity.getDividend())
                            .build());
        }


        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(), dividends);
    }
}
