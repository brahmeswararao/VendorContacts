package com.example.vendorcontacts.web;

import com.example.vendorcontacts.model.Vendor;
import com.example.vendorcontacts.repo.VendorRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorRepository repository;

    public VendorController(VendorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Vendor> list(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> city,
            @RequestParam Optional<Boolean> active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        String[] sortParts = sort.split(",");
        String sortProp = sortParts[0];
        Sort.Direction direction = (sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1])) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortProp));
        return repository.search(name.orElse(null), city.orElse(null), active.orElse(null), pageable);
    }

    @GetMapping("/{id}")
    public Vendor get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not found: " + id));
    }

    @PostMapping
    public ResponseEntity<Vendor> create(@Valid @RequestBody Vendor vendor) {
        vendor.setId(null);
        Vendor saved = repository.save(vendor);
        return ResponseEntity.created(URI.create("/api/vendors/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Vendor update(@PathVariable Long id, @Valid @RequestBody Vendor vendor) {
        Vendor existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not found: " + id));
        existing.setName(vendor.getName());
        existing.setContactName(vendor.getContactName());
        existing.setPhone(vendor.getPhone());
        existing.setEmail(vendor.getEmail());
        existing.setAddress(vendor.getAddress());
        existing.setCity(vendor.getCity());
        existing.setState(vendor.getState());
        existing.setPostalCode(vendor.getPostalCode());
        existing.setCountry(vendor.getCountry());
        existing.setActive(vendor.isActive());
        return repository.save(existing);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable Long id, @RequestParam boolean active) {
        Vendor existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendor not found: " + id));
        existing.setActive(active);
        repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vendor not found: " + id);
        }
        repository.deleteById(id);
    }
}
