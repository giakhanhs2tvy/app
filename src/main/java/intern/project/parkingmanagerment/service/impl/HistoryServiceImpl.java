package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;
import intern.project.parkingmanagerment.model.Invoice;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.model.VehicleType;
import intern.project.parkingmanagerment.repositories.HistoryRepository;
import intern.project.parkingmanagerment.service.*;
import intern.project.parkingmanagerment.util.HistoryMapper;
import intern.project.parkingmanagerment.util.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {


    private final Logger logger = LogFactory.getLogger();

    @Autowired
    VehicleService vehicleService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    VehicleTypeService vehicleTypeService;

    @Autowired
    CardService cardService;

    @Autowired
    InvoiceService invoiceService;


    @Override
    public HistoryDto checkHistory(Long cardId) {
        History history = historyRepository.findHistoriesByCardIdAndTimeOutNull(cardId);
        if(history == null) {
            return null;
        }
        HistoryDto historyDto = new HistoryDto();
        historyDto.setLicensePlate(history.getVehicle().getLicensePlate());
        historyDto.setCardId(cardId);
        historyDto.setVehicleTypeId(history.getVehicle().getVehicleType().getVehicleTypeId());
        historyDto.setTimeIn(history.getTimeIn());
        historyDto.setTimeOut(new Date());
        return historyDto;
    }

    @Override
    public History checkHistoryEntity(Long cardId) {
        return historyRepository.findHistoriesByCardIdAndTimeOutNull(cardId);
    }

    @Override
    public History newHistory(History history) {

        return historyRepository.save(history);
    }

    @Override
    public History newHistoryByHistoryDto(HistoryDto historyDto) {
        History historyCheck = historyRepository.findHistoriesByCardIdAndTimeOutNull(historyDto.getCardId());
        logger.info(historyCheck);
        if (historyCheck == null) {
            historyCheck = new History();
            Vehicle vehicle = vehicleService.findByLicensePlate(historyDto.getLicensePlate());
            if(vehicle == null) {
                vehicle = new Vehicle();
                VehicleType vehicleType = vehicleTypeService.getVehicleType(historyDto.getVehicleTypeId());;
                vehicle.setLicensePlate(historyDto.getLicensePlate());
                vehicle.setVehicleType(vehicleType);
                vehicle = vehicleService.saveVehicle(vehicle);
            }
            historyCheck.setCard(cardService.findById(historyDto.getCardId()));
            historyCheck.setVehicle(vehicle);
            historyCheck.setTimeIn(historyDto.getTimeIn());
        } else {

        }
        historyCheck.setTimeOut(historyDto.getTimeOut());
        logger.info(historyCheck);
        return historyRepository.save(historyCheck);
    }

    @Override
    public HistoryDto newHistoryByHistoryDto2(HistoryDto historyDto) {
        History historyCheck = historyRepository.findHistoriesByCardIdAndTimeOutNull(historyDto.getCardId());
        logger.info(historyCheck);
        if (historyCheck == null) {
            historyCheck = new History();
            Vehicle vehicle = vehicleService.findByLicensePlate(historyDto.getLicensePlate());
            if(vehicle == null) {
                vehicle = new Vehicle();
                VehicleType vehicleType = vehicleTypeService.getVehicleType(historyDto.getVehicleTypeId());;
                vehicle.setLicensePlate(historyDto.getLicensePlate());
                vehicle.setVehicleType(vehicleType);
                vehicle = vehicleService.saveVehicle(vehicle);
            }
            historyCheck.setTimeOut(historyDto.getTimeOut());
            historyCheck.setCard(cardService.findById(historyDto.getCardId()));
            historyCheck.setVehicle(vehicle);
            historyCheck.setTimeIn(historyDto.getTimeIn());
            historyRepository.save(historyCheck);
            return historyDto;
        }
        historyCheck.setTimeOut(historyDto.getTimeOut());
        if(historyCheck.getVehicle().getContract() != null
                && historyCheck.getVehicle().getContract().getTimeExpired().before(new Date())) {
            historyDto.setHasContract(true);
            historyRepository.save(historyCheck);
            return historyDto;
        }
        historyDto.setHasContract(false);
        LocalDate localDate1 = historyCheck.getTimeIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = historyCheck.getTimeOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long differenceInDays = ChronoUnit.DAYS.between(localDate2, localDate1);
        Double price = (differenceInDays + 1) * historyCheck.getVehicle().getVehicleType().getPricePerDay();
        Invoice invoice = new Invoice();
        invoice.setPrice(price);
        invoice.setNumDay(differenceInDays + 1);
        invoice = invoiceService.saveInvoice(invoice);
        historyCheck.setInvoice(invoice);
        historyCheck = historyRepository.save(historyCheck);

        historyDto.setPrice(price);
        return historyDto;

    }

    @Override
    public List<HistoryDto> listHistoryDto() {
        List<History> historyList = historyRepository.findByOrderByUpdatedAtDesc();

        return HistoryMapper.toDTOList(historyList);
    }

    @Override
    public List<HistoryDto> listHistoryDtoByDate() {
        List<History> historyList = historyRepository.findByOrderByUpdatedAtDesc();

        return HistoryMapper.toDTOList(historyList);
    }

    @Override
    public List<HistoryDto> loadMoreHistoryDto(Long page) {
        return HistoryMapper.toDTOList(historyRepository.findByOrderByUpdatedAtDescOffset(page));
    }

    @Override
    public List<HistoryDto> listHistoryDtoByVehicleTypeId(Long vehicleTypeId) {
        List<History> historyList = historyRepository.findByVehicleType(vehicleTypeId);
        return HistoryMapper.toDTOList(historyList);
    }
}
